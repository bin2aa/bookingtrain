package com.example.train.Controller;

import com.example.train.Model.CarriageTypeModel;
import com.example.train.Model.StationModel;
import com.example.train.Model.TicketInfoModel;
import com.example.train.Service.CarriageTypeService;
import com.example.train.Service.StationService;
import com.example.train.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StationService stationService;

    @Autowired
    private CarriageTypeService carriageTypeService;

    @GetMapping("/home")
    public String showSearchForm(Model model) {
        List<StationModel> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);

        List<CarriageTypeModel> carriageTypes = carriageTypeService.getAllCarriageTypes();
        model.addAttribute("carriageTypes", carriageTypes);
        return "Client/Components/Home";
    }

    @GetMapping("/signin")
    public String showLogin() {
        return "Client/Components/Login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "Client/Components/Register";
    }
}
