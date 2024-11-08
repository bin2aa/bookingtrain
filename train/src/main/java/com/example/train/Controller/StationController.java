package com.example.train.Controller;

import com.example.train.Model.StationModel;
import com.example.train.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class StationController {
    @Autowired
    private StationService stationService;


}
