package org.ii.kucharyna.app;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.ii.kucharyna.persistance.KucharynaRepository;
import org.ii.kucharyna.persistance.communication.MongoLabCommunicator;

import android.util.Log;


public class BigBadStaticThing {
  public static MongoLabCommunicator communicator = new MongoLabCommunicator();
  public static KucharynaRepository repository = new KucharynaRepository(communicator);
  
  static {
    try {
      repository.fillRecipes();
      Log.e("BigBadStaticThing", Integer.toString(repository.getRecipes().size()));
    } catch (Exception e) {
      StringWriter writer = new StringWriter();
      e.printStackTrace(new PrintWriter(writer));
      Log.e("BigBadStaticThing", writer.toString());
    }
  }
}
