package com.meggo.tp_hospital.web;

import com.meggo.tp_hospital.entities.Patient;
import com.meggo.tp_hospital.repositories.PatienRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatienRepository patienRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Patient> patients = patienRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listPatients", patients);
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id, int page, String keyword) {
        patienRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }
    @PostMapping("/savePatient")
    public String savePatient(Patient patient) {
        patienRepository.save(patient);
        return "formPatient";
    }
}
