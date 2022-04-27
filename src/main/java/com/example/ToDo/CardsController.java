package com.example.ToDo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardsController {

    ArrayList<String[]> cards;

    public CardsController(){
        setCards(new ArrayList<String[]>());
        
        String[] card1 = new String[2];
        card1[0] = "Ein schöner Titel";
        card1[1] = "Hier steht dann der ganze Text";
        getCards().add(card1);

        String[] card2 = new String[2];
        card2[0] = "Ein Haus mit einer Maus";
        card2[1] = "Die Maus mag Käse, aber keinen Cheddar.";
        getCards().add(card2);

    }

    

    @GetMapping("/cards")
    public String cards(@RequestParam(name="activePage", required = false, defaultValue = "cards") String activePage, Model model){
        model.addAttribute("activePage", "cards");
        model.addAttribute("cards", getCards());
        return "index.html";
    }

    public void setCards(ArrayList<String[]> cards) {
        this.cards = cards;
    }

    public ArrayList<String[]> getCards() {
        return cards;
    }
}
