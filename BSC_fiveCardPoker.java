//Creator: BillySampleCode
//Project Name: BSC_fiveCardPoker

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BSC_fiveCardPoker {
    
    //CF&IF
    final static public String cardRankOptions[] = {"2","3","4","5","6","7",
                                "8","9","10","Jack", "Queen", "King", "Ace"};
    final static public String cardSuitOptions[] = {"Clubs","Diamonds",
                                "Hearts","Spades"};
    String cardRank;
    String cardSuit;
    int cardCount;
    String cardName;
    
    //Restrict ArrayList to ClassType.
    static ArrayList<BSC_fiveCardPoker> deckRandomArrayList = 
            new ArrayList<>();
    int deckLength = deckRandomArrayList.size();
    static boolean deckStatus = false; //To check if deck is full or not.
    static int cardCounter1 = 1; //To count the Nth random card.
    static boolean aCardStatus = false; //To check if card exists in deck.
    
    //CC: PCCDNA
    public BSC_fiveCardPoker(){
    };
    //CC: PCCWA ... Constructor for a card object.
    public BSC_fiveCardPoker(String rankInput, String suitInput,
                        int cardCounter1){
        this.cardRank = rankInput;
        this.cardSuit = suitInput;
        this.cardCount = cardCounter1;
        this.cardName = (this.cardRank + " of " + this.cardSuit);
    };  
    
    //All others.
    public static BSC_fiveCardPoker sfmCreateRandomCard(
                    String inputRank, String inputSuit, int inputCounter){
        BSC_fiveCardPoker newCard = 
                new BSC_fiveCardPoker(
                    inputRank, 
                    inputSuit,
                    inputCounter);
        return newCard;
    };
    
    public static boolean checkDeckArrayListSize(
                    ArrayList<BSC_fiveCardPoker> inputDeck){
        ArrayList<BSC_fiveCardPoker> aDeck = inputDeck;
    
        if (aDeck.size()== 52){
            deckStatus = true;
        }else if(aDeck.size()!= 52){
            deckStatus = false;
        }
        return deckStatus;
    };
    
    public static BSC_fiveCardPoker createRandomCard(){
        //Random: random.nextInt(inputIntExclusive)
        //...0inclusive to #exclusive.
        Random randomVar = new Random();
        int newRandom1 = randomVar.nextInt(cardRankOptions.length);
        int newRandom2 = randomVar.nextInt(cardSuitOptions.length);       
        String randomRank = cardRankOptions[newRandom1];
        String randomSuit = cardSuitOptions[newRandom2];
        //Use sfm here...
        BSC_fiveCardPoker cardObject =
                sfmCreateRandomCard(randomRank, randomSuit,
               cardCounter1);
        
        //cardCounter1 should increase after card creation and SOPCheck.
        cardCounter1++;
        return cardObject;
    };
    
    public static boolean checkForCard(BSC_fiveCardPoker inputCard){
        BSC_fiveCardPoker aCard = inputCard;
        int cardCounter2 = 0; //Tracks the number of repated cards.
        
        //Will run into problem if ArrayList is empty.
        if(deckRandomArrayList.size() == 0){
            aCardStatus = false;
        }else if(deckRandomArrayList.size() != 0){
            for(BSC_fiveCardPoker currentCard : deckRandomArrayList){
                if (aCard.cardRank.equals(currentCard.cardRank)
                    && aCard.cardSuit.equals(currentCard.cardSuit)){
                    cardCounter2++; //This counter should never be >1.
                }
            }
        }
        if(cardCounter2 > 0){
            aCardStatus = true;
        }else{
            aCardStatus = false;
        }
        //Count the Nth random card here.   
        return aCardStatus;
    };
        
    //Utilize @Override so values returned in ArrayList are not hashcodes.
    @Override
    public String toString(){
        return this.cardName;
    };
    
    //Ask For Scanner Input, to determine number of player hands!
    static int handsAtTable = 0;
    static boolean handsMaxLimit = true; //anchorLocal.
    
    public static void askForScannerInput(){
        int intCheck = 0; //anchorLocal.
        boolean inputStatus = false; //anchorLocal.
        
        do{
            Scanner scannerVar1 = new Scanner(System.in);
            System.out.println("Please input number of players: ");
            //Assume string, convert later.
            String input1 = scannerVar1.nextLine();
            try{
                //Parse a String into an Integer Wrapper Value.
                handsAtTable = Integer.parseInt(input1);
                inputStatus = true;
                    if (handsAtTable > 4){
                    System.out.println("Too many, max players 4, "
                            + "please re-input!");
                    inputStatus = false;
                    }else if(handsAtTable == 0){
                    System.out.println("Huh, you entered zero players! "
                            + "please re-input!"); 
                    inputStatus = false;        
                    }else if(handsAtTable == 1){
                    System.out.println("Thanks, we have " + handsAtTable 
                        + " player! - Why not invite a friend to join!");    
                    inputStatus = true;
                    }else{
                    System.out.println("Thanks, we have " + handsAtTable 
                        + " players!");    
                    inputStatus = true;
                    }
            }catch(NumberFormatException varException){
                System.out.println("You did not enter an integer!");
                inputStatus = false;
            }
        }while(inputStatus == false);
    }
    
    //Create sfm for creating an arrayListHand.
    public static ArrayList<BSC_fiveCardPoker> sfmCreateHand(){
        ArrayList<BSC_fiveCardPoker> aHand = new ArrayList();
        return aHand;
    };
    
    //Create a 2D ArrayList, an ArrayList filled with ArrayLists!
    static ArrayList<ArrayList<BSC_fiveCardPoker>> al2DHandCollector 
            = new ArrayList();
    
    //Consolidate the number of player hands created.
    public static void createHandsAtTableCollector(){
        int handCounter = 0; //anchorLocal.
        while (handCounter<handsAtTable){
            ArrayList<BSC_fiveCardPoker> newHand = sfmCreateHand();
            //Add each new hand at the Nth index of the arraylist collector.
            al2DHandCollector.add(handCounter,newHand);
            handCounter++;
        }
    };
    
    //Draw card from deck.
    public static BSC_fiveCardPoker drawRandomCard(){
        Random randomVar = new Random();
        int newRandom3 = randomVar.nextInt(deckRandomArrayList.size());
        BSC_fiveCardPoker addThisCardToHand = 
                deckRandomArrayList.get(newRandom3);
        return addThisCardToHand;
    };
    
    //Remove drawn card from existing deck.
    public static void removeDrawnRandomCard(BSC_fiveCardPoker inputCard){
        deckRandomArrayList.remove(inputCard);
    };
    
    //A single round of drawing cards.
    static int cardCounter3 = 0; //anchorLocal for each round of card dealt.
    
    public static void aRoundOfDrawingCards(){
        for(ArrayList<BSC_fiveCardPoker> 
                handArrayList: al2DHandCollector ){
            BSC_fiveCardPoker drawnCard = drawRandomCard();
            handArrayList.add(cardCounter3, drawnCard);
            removeDrawnRandomCard(drawnCard);
        }
        //Increase counter for next index in each hand.
        cardCounter3++;
    };
    
    //Set the number of cards per hand.
    static int cardsPerHand = 5;
    //A multiple round of drawing cards to build a hand.
    public static void buildTheHands(){
        int nThRound = 0;
        while(nThRound < cardsPerHand){
            aRoundOfDrawingCards();
            nThRound++;
        }
    };
    
    //SOP Checks.
    public static void sopCheck1(){
        System.out.println("Hello, world!");
        System.out.println("Welcome to BillySampleCode's "
                + "Five Card Poker Game!");
    }
    
    public static void sopCheck2(){
        System.out.println("Cards are dealt! Number of cards remaining in "
                + "deck: " + deckRandomArrayList.size());            
    };
    
    public static void sopCheck3(){
        int cardCounter4 = 0; //anchorLocal.
        for (ArrayList<BSC_fiveCardPoker> 
                handArrayList: al2DHandCollector){
            System.out.println("Player #" + (cardCounter4+1) + "'s cards are:"
                    + handArrayList);
            cardCounter4++;
        }
    }
    public static void sopCheck4(){
        System.out.println("Please compare for strongest hand! ");
        System.out.println("Thanks for playing!");
        System.out.println("Hope you had plenty of fun!");
    }
    
    static boolean psvmVar1;
    public static void main (String[] args){
        //Building a random deck.
        //Check if deck is full.
        psvmVar1 = checkDeckArrayListSize(deckRandomArrayList);
        
        //If full, do nothing.
        if (psvmVar1 == true){
            return;
        //If not full, then Loop-While, until deck is full.
        }else if (psvmVar1 == false){
            labelPoint1:
            while(deckRandomArrayList.size()<52){
                //Create a random card. 
                BSC_fiveCardPoker psvmVar2 = createRandomCard();
                //Check if random card exists in deck.
                boolean psvmVar3 = checkForCard(psvmVar2);
                //If in deck, jump back to create random card.
                if (psvmVar3 == true){
                    continue labelPoint1;
                }
                //If not in deck, then add random card to deck.
                deckRandomArrayList.add(psvmVar2);
            }
        }
        
        //Run the game.
        sopCheck1();
        askForScannerInput();
        createHandsAtTableCollector();
        buildTheHands();
        sopCheck2();
        sopCheck3();
        sopCheck4();
    };
};//End.