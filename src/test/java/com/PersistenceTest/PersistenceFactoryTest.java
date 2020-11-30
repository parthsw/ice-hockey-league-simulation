package com.PersistenceTest;

import com.Persistence.ILeaguePersistence;
import com.Persistence.IPersistenceFactory;

public class PersistenceFactoryTest implements IPersistenceFactory {
    private ILeaguePersistence leaguePersistence = null;
    private ConferencePersistenceMock conferencePersistence = null;
    private DivisionPersistenceMock divisionPersistence = null;
    private TeamPersistenceMock teamPersistence = null;
    private TeamPlayerPersistenceMock teamPlayerPersistence = null;
    private FreeAgentPersistenceMock freeAgentPersistence = null;
    private CoachPersistenceMock coachPersistence = null;
    private ManagerPersistenceMock managerPersistence = null;
    private GamePlayConfigPersistenceMock gamePlayConfigPersistence = null;

    public ILeaguePersistence createLeaguePersistence() {
        if (leaguePersistence == null) {
            leaguePersistence = new LeaguePersistenceMock();
        }
        return leaguePersistence;
    }

    public ConferencePersistenceMock createConferencePersistence() {
        if (conferencePersistence == null) {
            conferencePersistence = new ConferencePersistenceMock();
        }
        return conferencePersistence;
    }

    public DivisionPersistenceMock createDivisionPersistence() {
        if (divisionPersistence == null) {
            divisionPersistence = new DivisionPersistenceMock();
        }
        return divisionPersistence;
    }

    public TeamPersistenceMock createTeamPersistence() {
        if (teamPersistence == null) {
            teamPersistence = new TeamPersistenceMock();
        }
        return teamPersistence;
    }

    public TeamPlayerPersistenceMock createTeamPlayerPersistence() {
        if (teamPlayerPersistence == null) {
            teamPlayerPersistence = new TeamPlayerPersistenceMock();
        }
        return teamPlayerPersistence;
    }

    public FreeAgentPersistenceMock createFreeAgentPersistence() {
        if (freeAgentPersistence == null) {
            freeAgentPersistence = new FreeAgentPersistenceMock();
        }
        return freeAgentPersistence;
    }

    public CoachPersistenceMock createCoachPersistence() {
        if (coachPersistence == null) {
            coachPersistence = new CoachPersistenceMock();
        }
        return coachPersistence;
    }

    public ManagerPersistenceMock createManagerPersistence() {
        if (managerPersistence == null) {
            managerPersistence = new ManagerPersistenceMock();
        }
        return managerPersistence;
    }

    public GamePlayConfigPersistenceMock createGamePlayConfigPersistence() {
        if (gamePlayConfigPersistence == null) {
            gamePlayConfigPersistence = new GamePlayConfigPersistenceMock();
        }
        return gamePlayConfigPersistence;
    }
}
