package engine.mapTools;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import engine.GameObject;
import engine.Screen;
import engine.Systems.*;
import engine.support.Vec2d;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tic.GameScreen;

import java.io.IOException;
import java.util.Objects;

public class XMLReader {

    DocumentBuilderFactory factory;
    DocumentBuilder docBuilder;
//    Document doc = docBuilder.parse("engine/mapTools/TestMap.xml");
    Document doc;
    Screen myScreen;

    public XMLReader(String url, Screen myScreen) throws ParserConfigurationException, IOException, SAXException {
        factory = DocumentBuilderFactory.newInstance();
        docBuilder = factory.newDocumentBuilder();
        doc = docBuilder.parse(url);
        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getChildNodes().getLength());
        this.myScreen = myScreen;
    }

    public void readXML(){
        NodeList systems = doc.getElementsByTagName("Systems");
        // CollisionSystem
        CollisionSystem collisionSystem = new CollisionSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(collisionSystem);
        DrawSystem drawSystem = new DrawSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(drawSystem);
        GameSystem gameSystem = new GameSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(gameSystem);
        KeyPressSystem keyPressSystem = new KeyPressSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(keyPressSystem);
        MouseUpdateSystem mouseUpdateSystem = new MouseUpdateSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(mouseUpdateSystem);
        PhysicsSystem physicsSystem = new PhysicsSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(physicsSystem);
        ResizeSystem resizeSystem = new ResizeSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(resizeSystem);
        SpriteSystem spriteSystem = new SpriteSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(spriteSystem);
        TickSystem tickSystem = new TickSystem(myScreen.gameManager);
        myScreen.gameManager.addSystem(tickSystem);


        NodeList objectList = doc.getElementsByTagName("Object");
        int index = 0;
        while (index < objectList.getLength()) {
            Node n = objectList.item(index);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                Vec2d location = new Vec2d(Double.parseDouble(e.getAttribute("x")), Double.parseDouble(e.getAttribute("y")));
                Vec2d dimensions = new Vec2d(Double.parseDouble(e.getAttribute("width")), Double.parseDouble(e.getAttribute("height")));
                GameObject toCreate = new GameObject(location, dimensions, new Vec2d(0.0), Color.BLACK);
                myScreen.gameManager.gameObjects.add(toCreate);
                toCreate.addSystem(tickSystem, 3.0);
                toCreate.addSystem(drawSystem, 3.0);
            }
            index++;
        }



    }


}
