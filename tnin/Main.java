package tnin;

import engine.support.FXApplication;
import engine.support.FXFrontEnd;
import engine.support.Vec2d;
import org.xml.sax.SAXException;
import tnin.App;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Vec2d openingSize = new Vec2d(1600, 900);
        FXFrontEnd app = new App("Nin" ,openingSize, true, false);
        FXApplication application = new FXApplication();
        application.begin(app);
    }

}
