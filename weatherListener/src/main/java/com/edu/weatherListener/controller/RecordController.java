package com.edu.weatherListener.controller;

import com.edu.weatherListener.domain.Record;
import com.edu.weatherListener.domain.User;
import com.edu.weatherListener.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RecordController {

    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/")
    public String getRecords(
            @RequestParam(required = false) Record record,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 25) Pageable pageable
    ) {

        Page<Record> page = recordService.findAllByDateStartingWith(filter,pageable);

        model.addAttribute("record", record);
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);

        return "records";

    }


    @PostMapping("/")
    public String commitRecord(
            @AuthenticationPrincipal User currentUser,
            @Valid Record record,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 25) Pageable pageable
    ) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            Page<Record> page = recordService.findAll(pageable);

            model.addAttribute("record", record);
            model.addAttribute("page", page);
            return "records";

        } else {
            record.setAuthor(currentUser);
            model.addAttribute("record", null);
            recordService.save(record);

            Page<Record> records = recordService.findAll(pageable);

            model.addAttribute("records", records);
        }

        return "redirect:/";

    }

    @PostMapping("/delete")
    public String deleteRecord(
            @AuthenticationPrincipal User currentUser,
            @RequestParam Long id
    ) {
        recordService.deleteById(id,currentUser.getId());
        return "redirect:/";
    }

}
