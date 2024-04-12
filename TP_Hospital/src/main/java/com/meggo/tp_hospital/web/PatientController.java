package com.meggo.tp_hospital.web;

import com.meggo.tp_hospital.entities.Patient;
import com.meggo.tp_hospital.repositories.PatienRepository;
import com.meggo.tp_hospital.security.services.AccountServiceImpl;
import com.meggo.tp_hospital.security.services.UserDetailsServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class PatientController {
    PatienRepository patientRepository;
    @GetMapping("/user/index")
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients);
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }
    @GetMapping("/admin/formPatient")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping("/admin/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/user/index?keyword="+patient.getNom();
    }
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model,Long id){
        Patient patient=patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        return "editPatient";
    }
    @PostMapping("/admin/editPatient")
    public String updatePatient(@Valid Patient patient, BindingResult bindingResult, Long id){
        if (bindingResult.hasErrors()){
            return "editPatient";
        }
        Patient toModify = patientRepository.findById(id).get();

        toModify.setDateNaissance(patient.getDateNaissance());
        toModify.setMalade(patient.isMalade());
        toModify.setScore(patient.getScore());
        toModify.setNom(patient.getNom());
        patientRepository.save(toModify);
        return "redirect:/user/index?keyword="+patient.getNom();
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String authorities = userDetails.getAuthorities().stream().map(auth -> auth.getAuthority().split("_")[1]).collect(Collectors.joining(" "));
            // You can access other user details as needed
            model.addAttribute("user", username);
            model.addAttribute("authorities", authorities);
        }

        return "profile";
    }

}