package com.example.ToDo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForController {
    
    ArrayList<String[]> cards;

    public ForController(){
        setCards(new ArrayList<String[]>());
        createDemoCards();
    }

    private void createDemoCards(){
        String[] card1 = new String[2];
        card1[0] = "Fische";
        card1[1] = "Fische sind total super! Die können unter Wasser atmen!";
        getCards().add(card1);


        String[] card2 = new String[2];
        card2[0] = "Hunde";
        card2[1] = "Hunde müssen ihr Leben lang zum Geschäft nach draußen gebracht werden...";
        getCards().add(card2);

        String[] card3 = new String[2];
        card3[0] = "Katzen";
        card3[1] = "Katzen sind im allgemein sehr verkuschelt und doch echt niedlich, oder?";
        getCards().add(card3);

        String[] card4 = new String[2];
        card4[0] = "Mäuse";
        card4[1] = "Die essen gerne Käse, aber nerven auch ordentlich wenn sie in der Garage knabbern.";
        getCards().add(card4);

        String[] card5 = new String[2];
        card5[0] = "Fliegen";
        card5[1] = "Putzen Pferde usw., sind aber auch eher lästig.";
        getCards().add(card5);

        String[] card6 = new String[2];
        card6[0] = "Vögel";
        card6[1] = "Dazu kann ich nicht viel sagen. Wellensittich.";
        getCards().add(card6);
        
    }

    @GetMapping("/forexample")
    public String showForExample(@RequestParam(name="activePage", required = false, defaultValue = "forexample") String activePage, Model model){
        model.addAttribute("activePage", "forexample");
        model.addAttribute("cards", getCards());
        return "index.html";
    }

    public ArrayList<String[]> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String[]> cards) {
        this.cards = cards;
    }   
}