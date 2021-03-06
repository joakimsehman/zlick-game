package gui;

import database.HostGameManager;
import game.Model;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by joakim on 2015-12-08.
 */
public class GamesViewer extends GuiEntity {

    private ArrayList<GameEntity> gameEntities;

    private GameEntity selectedGame;

    public GamesViewer(int xPos, int yPos) {
        super(xPos, yPos);

        selectedGame = null;

        List<HostGameManager.GameTemplate> hostedGames = HostGameManager.getInstance().getHostedGames();

        gameEntities = new ArrayList<GameEntity>();



        int gameCounter = 0;
        for(int i = 0; i < hostedGames.size(); i++){

            HostGameManager.GameTemplate currentGame = hostedGames.get(i);

            GameEntity gameEntity = new GameEntity(getxPos(), getyPos() + 20*gameCounter, 500, 20, currentGame.getUser(), currentGame.getGlobalip(), currentGame.getLocalip());

            gameEntities.add(gameEntity);

            gameCounter++;
        }
    }

    @Override
    public void draw(Graphics g) {

        for(int i = 0; i < gameEntities.size(); i++){
            gameEntities.get(i).draw(g);
        }
    }

    @Override
    protected void update(int delta) {
        if(Mouse.isButtonDown(0)){
            for(int i = 0; i < gameEntities.size(); i++){
                GameEntity gameEntity = gameEntities.get(i);
                if(gameEntity.intersects(Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY())){
                    if(selectedGame != null){
                        selectedGame.setSelectedGame(false);
                    }
                    selectedGame = gameEntity;
                    selectedGame.setSelectedGame(true);
                }
            }
        }
    }

    @Override
    protected boolean isSelectable() {
        return false;
    }

    @Override
    public boolean intersects(int x, int y) {
        return false;
    }

    public String getSelectedGameGlobalIp(){
        if(selectedGame != null) {
            return selectedGame.getGlobalip();
        }else{
            return "";
        }
    }

    public String getSelectedGameLocalIp(){
        if(selectedGame != null) {
            return selectedGame.getLocalip();
        }else{
            return "";
        }
    }




    private class GameEntity extends GuiEntity{

        private String user, globalip, localip;
        private int width, height;
        private boolean isSelected;

        public GameEntity(int xPos, int yPos, int width, int height, String user, String globalip, String localip){
            super(xPos, yPos);
            this.user = user;
            this.globalip = globalip;
            this.localip = localip;
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw(Graphics g) {
            if(!isSelected) {
                g.setColor(Color.black);
                g.drawString(user + "       " + globalip + "        " + localip, getxPos(), getyPos());
            }else{
                g.setColor(Color.black);
                g.fillRect(getxPos(), getyPos(), width, height);
                g.setColor(Color.white);
                g.drawString(user + "       " + globalip + "        " + localip, getxPos(), getyPos());
            }
        }

        @Override
        protected void update(int delta) {

        }

        @Override
        protected boolean isSelectable() {
            return false;
        }

        @Override
        public boolean intersects(int x, int y) {
            if(x > this.getxPos() && y > this.getyPos() && x <= this.getxPos() + width && y <= this.getyPos() + height){
                return true;
            }else{
                return false;
            }
        }

        public void setSelectedGame(boolean selected){
            isSelected = selected;
        }

        public String getUser(){
            return user;
        }

        public String getGlobalip(){
            return globalip;
        }

        public String getLocalip(){
            return localip;
        }
    };
}
