package com.IceHockeyLeague.LeagueManager.DraftPick;

import java.util.List;

public interface IDraftPickModel {
    void addDraftTrade(IDraftPick draft);

    void clearDraftTrade();

    void addDrafts(List<IDraftPick> drafts);
}
