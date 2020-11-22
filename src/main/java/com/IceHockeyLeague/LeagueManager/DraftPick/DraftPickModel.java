package com.IceHockeyLeague.LeagueManager.DraftPick;

import java.util.ArrayList;
import java.util.List;

public class DraftPickModel implements IDraftPickModel {
    private List<IDraftPick> draftList = new ArrayList<>();

    @Override
    public void addDraftTrade(IDraftPick draft) {
        this.draftList.add(draft);
    }

    @Override
    public void clearDraftTrade() {
        this.draftList.clear();
    }

    @Override
    public void addDrafts(List<IDraftPick> drafts) {
        this.draftList.addAll(drafts);
    }
}
