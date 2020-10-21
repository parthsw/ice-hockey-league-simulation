package main.trading.playerHandler;

public class players {
    private int playerId;
    private boolean freeAgent;
    private boolean consistent = true;

    public players(int playerId, boolean freeAgent) throws playerExceptions {
        this.playerId = playerId;
        this.freeAgent = freeAgent;

        if(freeAgent){
            String table = "freeTable";
        }else{
            String table = "playerTable";
        }

        if(this.playerExists(this.playerId)){
            this.getPlayerInfo(this.playerId);
        }else{
            throw new playerExceptions("Player does not Exist");
        }
    }

    private boolean playerExists(int playerId){
        return true;
    }

    private void getPlayerInfo(int playerId){
        //gather and fill player data.
    }

    private void addToFreeAgent(){
        // Take player attribute and add into freeAgent table.
    }

    private void removeFromFreeAgent(int playerId){
        //remove player from freeAgent table.
    }

    private void addToPlayer(){
        // Take player attribute and add into player table.
    }

    private void removeFromPlayer(int playerId){
        // Remove player from playerTable.
    }

    public void addToTeam(int teamId){
        // add into team
        this.getlock();
        this.removeFromFreeAgent(playerId);
        this.addToPlayer();
        this.releaselock();
    }

    public void removeFromTeam(){
        //remove from team
        this.getlock();
        this.removeFromPlayer(playerId);
        this.addToFreeAgent();
        this.releaselock();
    }

    private void getlock(){
        this.consistent = false;
    }

    private void releaselock(){
        this.consistent = true;
    }

    public boolean isConsistent(){
        return this.consistent;
    }

}
