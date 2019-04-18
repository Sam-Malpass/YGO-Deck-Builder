package programFunctions.utilities;

import dataStructure.banList.BanList;
import dataStructure.banList.LimitedCard;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Series;
import programFunctions.ProgramFunctions;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public Generator(){

    }
    /**
     * Function definition for genSDREAlbum()
     * <p>
     *      Generates an album with the SDRE cards
     * </p>
     */
    public void genSDREAlbum() {
        if(ProgramFunctions.profileActive()) {
            ProgramFunctions.createAlbum("exampleAlbum");
            /*Output beginning of generator*/
            System.out.println("#####    GENERATOR   #####");
            Series loaded = ProgramFunctions.getUtilities().getFileHandler().loadSeries("SDRE-EN");
            ArrayList<Card> card = loaded.getCards();
            for (Card c : card) {
                ProgramFunctions.getProgramData().getCurrentProfile().determineContainer("exampleAlbum").addCard(c);
                System.out.print("#");
            }
            System.out.println("#####    GENERATION COMPLETE     #####");
            ProgramFunctions.getUtilities().getFileHandler().saveUserProfile(ProgramFunctions.getProgramData().getCurrentProfile());
            ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
            ProgramFunctions.getProgramData().getUserInterface().updateTitle();
        }
        else {

        }
    }
    public void genBanList() {
        Random rnd = new Random();
        BanList banList = new BanList();
        ArrayList<LimitedCard> cards = new ArrayList<>();
        for(String S : ProgramFunctions.getUtilities().getFileHandler().searchSeriesFolder()) {
            for(int i = 0; i < 10; i++) {
                int tmp = ProgramFunctions.getUtilities().getFileHandler().loadSeries(S).getCards().size();
                int index = rnd.nextInt(tmp);
                String name = ProgramFunctions.getUtilities().getFileHandler().loadSeries(S).getCards().get(index).getCardName();
                int numCopies = rnd.nextInt(3)+1;
                LimitedCard C = new LimitedCard(name, numCopies);
                cards.add(C);
            }
        }
        banList.setList(cards);
        banList.setName("banList");
        ProgramFunctions.getUtilities().getFileHandler().saveBanList(banList);
    }
}
