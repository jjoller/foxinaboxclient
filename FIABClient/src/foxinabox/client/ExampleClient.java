package foxinabox.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foxinabox.server.ai.playermodels.RandomPlayerModel;
import foxinabox.server.ai.playermodels.SimpleModelProvider;
import foxinabox.server.poker.texaslimit.game.RandomDealer;
import foxinabox.server.poker.texaslimit.game.Table;
import foxinabox.server.poker.texaslimit.hand.LimitHand;
import foxinabox.server.poker.texaslimit.hand.Player;
import foxinabox.server.poker.texaslimit.hand.PlayerImpl;
import foxinabox.server.poker.texaslimit.hand.PlayerModel;
import foxinabox.server.webinterface.FIABPlayerModel;

public class ExampleClient {

	public static void main(String[] args) {

		Map<String, PlayerModel> models = new HashMap<String, PlayerModel>();
		models.put("RandomPlayer", new RandomPlayerModel());
		models.put("FIABServer", new FIABPlayerModel("testkey", "mytable"));

		int stack = 10000;
		List<Player> players = new ArrayList<Player>();
		players.add(new PlayerImpl("RandomPlayer", stack));
		players.add(new PlayerImpl("FIABServer", stack));

		// set up table
		Table table = new Table(new SimpleModelProvider(models), new LimitHand(
				new RandomDealer(), players));

		for (int k = 0; k < 20000; k++) {
			table.performStep();
			System.out.println((table.getCurrHand()));
		}

	}

}
