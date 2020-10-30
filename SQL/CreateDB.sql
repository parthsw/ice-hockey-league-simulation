USE CSCI5308_6_DEVINT;

CREATE TABLE league
(
    leagueID INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(45) NOT NULL,
    CONSTRAINT name_UNIQUE
        UNIQUE (name)
);

CREATE TABLE conference
(
    conferenceID INT AUTO_INCREMENT PRIMARY KEY,
    leagueID     INT         NOT NULL,
    name         VARCHAR(45) NOT NULL,
    CONSTRAINT conferenceToLeague
        FOREIGN KEY (leagueID) REFERENCES league (leagueID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE division
(
    divisionID   INT AUTO_INCREMENT PRIMARY KEY,
    conferenceID INT         NOT NULL,
    name         VARCHAR(45) NOT NULL,
    CONSTRAINT divisionToConference
        FOREIGN KEY (conferenceID) REFERENCES conference (conferenceID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE team
(
    teamID          INT AUTO_INCREMENT PRIMARY KEY,
    divisionID      INT         NOT NULL,
    teamName        VARCHAR(45) NOT NULL,
    isUserCreated   TINYINT(1)  NOT NULL,
    strength        FLOAT(1)    NOT NULL,
    CONSTRAINT teamToDivision
        FOREIGN KEY (divisionID) REFERENCES division (divisionID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE freeagent
(
    freeAgentID             INT AUTO_INCREMENT PRIMARY KEY,
    leagueID                INT           NOT NULL,
    name                    VARCHAR(45)   NOT NULL,
    age                     INT           NOT NULL,
    elapsedDaysFromLastBDay INT           NOT NULL,
    isInjured               TINYINT(1)    NOT NULL,
    daysInjured             INT           NOT NULL,
    injuryDate              DATE          NULL,
    isRetired               TINYINT(1)    NOT NULL,
    retirementDate          DATE          NULL,
    position                VARCHAR(45)   NOT NULL,
    skating                 INT           NOT NULL,
    shooting                INT           NOT NULL,
    checking                INT           NOT NULL,
    saving                  INT           NOT NULL,
    strength                FLOAT(1)      NOT NULL,
    CONSTRAINT freeAgentToLeague
        FOREIGN KEY (leagueID) REFERENCES league (leagueID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE coach
(
    coachID  INT AUTO_INCREMENT PRIMARY KEY,
    teamID   INT         NULL,
    leagueID INT         NOT NULL,
    name     VARCHAR(45) NOT NULL,
    skating  FLOAT(1)    NOT NULL,
    shooting FLOAT(1)    NOT NULL,
    checking FLOAT(1)    NOT NULL,
    saving   FLOAT(1)    NOT NULL,
    CONSTRAINT coachToLeague
        FOREIGN KEY (leagueID) REFERENCES league (leagueID),
    CONSTRAINT coachToTeam
        FOREIGN KEY (teamID) REFERENCES team (teamID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE manager
(
    managerID INT AUTO_INCREMENT PRIMARY KEY,
    teamID    INT         NULL,
    leagueID  INT         NOT NULL,
    name      VARCHAR(45) NOT NULL,
    CONSTRAINT managerToLeague
        FOREIGN KEY (leagueID) REFERENCES league (leagueID)
            ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT managerToTeam
        FOREIGN KEY (teamID) REFERENCES team (teamID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE player
(
    playerID                INT AUTO_INCREMENT PRIMARY KEY,
    teamID                  INT             NOT NULL,
    name                    VARCHAR(45)     NOT NULL,
    captain                 TINYINT(1)      NOT NULL,
    age                     INT             NOT NULL,
    elapsedDaysFromLastBDay INT             NOT NULL,
    isInjured               TINYINT(1)      NOT NULL,
    daysInjured             INT             NOT NULL,
    injuryDate              DATE            NULL,
    isRetired               TINYINT(1)      NOT NULL,
    retirementDate          DATE            NULL,
    position                VARCHAR(45)     NOT NULL,
    skating                 INT             NOT NULL,
    shooting                INT             NOT NULL,
    checking                INT             NOT NULL,
    saving                  INT             NOT NULL,
    strength                FLOAT(1)        NOT NULL,
    CONSTRAINT playerToTeam
        FOREIGN KEY (teamID) REFERENCES team (teamID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE gamePlayConfig
(
    gamePlayConfigID            INT AUTO_INCREMENT PRIMARY KEY,
    leagueID                    INT         NOT NULL,
    averageRetirementAge        INT         NOT NULL,
    maximumAge                  INT         NOT NULL,
    randomWinChance             FLOAT(1)    NOT NULL,
    randomInjuryChance          FLOAT(2)    NOT NULL,
    injuryDaysLow               INT         NOT NULL,
    injuryDaysHigh              INT         NOT NULL,
    daysUntilStatIncreaseCheck  INT         NOT NULL,
    lossPoint                   INT         NOT NULL,
    randomTradeOfferChance      FLOAT(2)    NOT NULL,
    maxPlayersPerTrade          INT         NOT NULL,
    randomAcceptanceChance      FLOAT(2)    NOT NULL,
    CONSTRAINT gamePlayConfigToLeague
        FOREIGN KEY (leagueID) REFERENCES league (leagueID)
            ON UPDATE CASCADE ON DELETE CASCADE
);

