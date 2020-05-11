package ru.itis.javalab.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class UploadController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("files") MultipartFile[] multipartFiles) throws IOException {

        return new ModelAndView("redirect:/upload");

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadFile() {
        return new ModelAndView("upload_page");
    }
}
