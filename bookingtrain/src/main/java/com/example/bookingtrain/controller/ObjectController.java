package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Object;
import com.example.bookingtrain.service.PermissionService;
import com.example.bookingtrain.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/objects")
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping // Xử lý yêu cầu GET tới đường dẫn /objects
    public String getAllobjects(Model model) {
        List<Object> objects = objectService.getAllObjects(); // Lấy danh sách objects từ service
        model.addAttribute("objects", objects); // Thêm danh sách objects vào model
        return "list/objectList"; // Trả về template objectList.html
    }

    @GetMapping("/newObject") // Xử lý yêu cầu GET tới đường dẫn /objects/addobject
    public String addobjectForm(Model model) {
        model.addAttribute("object", new Object()); // Thêm một object mới vào model
        model.addAttribute("permissions", permissionService.getAllPermissions());
        return "add/addObject"; // Trả về template addobject.html
    }

    @PostMapping("/addObject") // Xử lý yêu cầu POST tới đường dẫn /objects/addobject
    public String addObject(@ModelAttribute Object object) {
        objectService.createObject(object);
        return "redirect:/objects"; // Sau khi thêm object xong, chuyển hướng về trang danh sách object
    }

    @GetMapping("/editObject/{objectId}")
    public String editObjectForm(@PathVariable Integer objectId, Model model) {
        Object object = objectService.getObjectById(objectId);
        model.addAttribute("object", object);
        return "edit/editObject";
    }

    @PostMapping("/edit")
    public String editObject(@ModelAttribute Object object) {
        objectService.updateObject(object);
        return "redirect:/objects";
    }

    @GetMapping("/deleteobject/{id}")
    public String deleteObject(@PathVariable Integer id) {
        objectService.deleteObject(id);
        return "redirect:/objects";
    }

}