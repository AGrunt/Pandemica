package com.pandemica.app.pandemicapp.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pandemica.app.pandemicapp.service.PandemicaappService;
import com.pandemica.app.pandemicapp.entity.Patient;
import com.pandemica.app.pandemicapp.util.GeneratePdfReport;



@RestController 
@RequestMapping("/patient")
public class PandemicaappController {
	@Autowired
	private PandemicaappService pandemicService;
	
	@RequestMapping(value="/homePage", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
	public ModelAndView getVaccinationBooking(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		return modelAndView;
	}
	
	@RequestMapping(value="/findCenter", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
	public ModelAndView getVaccinationBookingMaps(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("map");
		return modelAndView;
	}

	@RequestMapping(value="/vaccinated", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
	public ModelAndView getVaccinated(Model model) {
		model.addAttribute("patients",pandemicService.getVaccinatedPatientList());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("vaccinationSummary");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> VaccinationReport() {
		List<Patient> patients = new ArrayList<>();
		// hard coded
         String name = "Muralikrishnan"; 
		patients = pandemicService.getVaccinatedPatient(name);

        ByteArrayInputStream bis = GeneratePdfReport.vaccinationReport(patients);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=VaccinationCertificate.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@RequestMapping(value="/saveBooking", method={RequestMethod.POST,RequestMethod.GET, RequestMethod.PUT}) 
    public ModelAndView saveBooking(Patient p,@RequestParam("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") Date date)
    {
		System.out.println(p.getEmailId() + p.getName() + p.getVenue() + p.getDate());
		pandemicService.saveBooking(p);
		ModelAndView modelAndView = new ModelAndView();    
		modelAndView.setViewName("bookConfirmation");        
		modelAndView.addObject("Patient", p);      
		return modelAndView;
    }
	
	@GetMapping("/register")
	@ResponseBody
	public ModelAndView showForm(Patient p) {
		ModelAndView modelAndView = new ModelAndView();    
		modelAndView.setViewName("bookVaccination");        
		modelAndView.addObject("Patient", p);      
		return modelAndView;
	}

}
