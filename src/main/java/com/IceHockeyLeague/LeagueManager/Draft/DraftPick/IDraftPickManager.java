package com.IceHockeyLeague.LeagueManager.Draft.DraftPick;

import java.util.List;

public interface IDraftPickManager {
    void addDraftPick(IDraftPick draft);

    void clearDraftPicks();

    void addDraftPicks(List<IDraftPick> drafts);

    List<IDraftPick> getDraftPicks();
}
