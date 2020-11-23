package com.IceHockeyLeague.LeagueManager.Draft.DraftPick;

import java.util.ArrayList;
import java.util.List;

public class DraftPickManager implements IDraftPickManager {
    private List<IDraftPick> futureDraftList = new ArrayList<>();

    @Override
    public void addDraftPick(IDraftPick draft){
        this.futureDraftList.add(draft);
    }

    @Override
    public void clearDraftPicks() {
        this.futureDraftList.clear();
    }

    @Override
    public void addDraftPicks(List<IDraftPick> drafts) {
        this.futureDraftList.addAll(drafts);
    }

    @Override
    public List<IDraftPick> getDraftPicks() {
        return this.futureDraftList;
    }
}
