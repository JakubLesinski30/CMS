package Twoj.Blog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller 
public class KontrolerArtykulu {
    
private final RepozytoriumArtykulu artykulRepo;

    @Autowired
    public KontrolerArtykulu(RepozytoriumArtykulu artykulRepo) {
        this.artykulRepo = artykulRepo;
    }

    @GetMapping("/")
    public String listaArtykulow(Model model) {
        model.addAttribute("artykuly", artykulRepo.findAll());
        return "index";
    }

    @GetMapping("/edytuj")
    public String edytuj(Model model) {
        model.addAttribute("artykuly", artykulRepo.findAll());
        return "edytuj";
    }

    @GetMapping("/artykul/{id}")
    public String pokazArtykul(@PathVariable Long id, Model model) {
        Artykul article = artykulRepo.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono"));
        model.addAttribute("artykuly", article);
        return "artykul";
    }

    @GetMapping("/artykul/nowy")
    public String nowyArtykul(Model model) {
        model.addAttribute("artykul", new Artykul());
        return "form";
    }

    @PostMapping("/artykul")
public String zapiszArtykul(@ModelAttribute Artykul artykul) {
    artykul.setData(new Date()); 
    artykulRepo.save(artykul);
    return "redirect:/";
}

    @GetMapping("/artykul/edytuj/{id}")
    public String edytujArtykul(@PathVariable Long id, Model model) {
        Artykul artykul = artykulRepo.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono"));
        model.addAttribute("artykul", artykul);
        return "form";
    }

    @GetMapping("/artykul/usun/{id}")
    public String usunArtykul(@PathVariable Long id) {
        artykulRepo.deleteById(id);
        return "redirect:/edytuj";
    }

}
