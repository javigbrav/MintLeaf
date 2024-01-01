import java.awt.*;
import javax.swing.*;

public class Community{
  private double rating = 0;
  public Community(){
    this.rating = 0;
  }

  public void recommendationQuiz() {
	  new RecommendationQuiz();
    }

    public void rateStory() {
        
    }

    public double averageRating() {
        
        return rating;
    }

    public void adjustRankings() {
        
    }

    public void reportStory() {
        
    }

    public void suggestStory() {
        
    }

    public boolean isApproved() {
    	
        boolean approved = false;
        return approved;
    }
}
