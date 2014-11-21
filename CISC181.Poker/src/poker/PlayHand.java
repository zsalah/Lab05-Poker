package poker;

import java.util.ArrayList;
import java.util.Collections;

import util.HibernateUtil;

import org.hibernate.*;

public class PlayHand {

	public static void main(String[] args) {



		for (int gCount = 0; gCount <= 2; gCount++) {
			ArrayList<Hand> Hands = new ArrayList<Hand>();
			Deck d = new Deck();
			
			Hand h = new Hand(d);
			Collections.sort(Hands, Hand.HandRank);
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			
			Pokerresults presults = new Pokerresults();
			presults.setHandstrength(h.getHandStrength());
			presults.setHihand(h.getHighPairStrength());
			presults.setLohand(h.getLowPairStrength());
			presults.setKicker(h.getKicker());
			
			
			int resultid = (Integer) session.save(presults);
			System.out.println(resultid);
			
			session.getTransaction().commit();

			

		}

	}

}
