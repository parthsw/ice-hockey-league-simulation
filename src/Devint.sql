create table league
(
    leagueID int auto_increment
        primary key,
    name     varchar(45) not null,
    constraint name_UNIQUE
        unique (name)
);

create table conference
(
    conferenceID int auto_increment
        primary key,
    leagueID     int         not null,
    name         varchar(45) not null,
    constraint conferenceToLeague
        foreign key (leagueID) references league (leagueID)
            on update cascade on delete cascade
);

create index conferenceToLeague_idx
    on conference (leagueID);

create table division
(
    divisionID   int auto_increment
        primary key,
    conferenceID int         not null,
    name         varchar(45) not null,
    constraint divisionToConference
        foreign key (conferenceID) references conference (conferenceID)
            on update cascade on delete cascade
);

create index divisionToConference_idx
    on division (conferenceID);

create table freeagent
(
    freeAgentID int auto_increment
        primary key,
    leagueID    int           not null,
    name        varchar(45)   not null,
    position    varchar(45)   not null,
    age         int           not null,
    skating     int           not null,
    shooting    int           not null,
    checking    int           not null,
    saving      int           not null,
    strength    decimal(3, 1) not null,
    isInjured   tinyint(1)    not null,
    constraint freeAgentToLeague
        foreign key (leagueID) references league (leagueID)
            on update cascade on delete cascade
);

create index freeAgentToLeague_idx
    on freeagent (leagueID);

create table team
(
    teamID     int auto_increment
        primary key,
    divisionID int         not null,
    teamName   varchar(45) not null,
    strength   int         not null,
    constraint teamName_UNIQUE
        unique (teamName),
    constraint teamToDivision
        foreign key (divisionID) references division (divisionID)
            on update cascade on delete cascade
);

create table coach
(
    coachID  int auto_increment
        primary key,
    teamID   int         null,
    leagueID int         not null,
    name     varchar(45) not null,
    skating  float       not null,
    shooting float       not null,
    checking float       not null,
    saving   float       not null,
    constraint coachToLeague
        foreign key (leagueID) references league (leagueID),
    constraint coachToTeam
        foreign key (teamID) references team (teamID)
            on update cascade on delete cascade
);

create index coachToLeague_idx
    on coach (leagueID);

create index coachToTeam_idx
    on coach (teamID);

create table manager
(
    managerID int auto_increment
        primary key,
    teamID    int         null,
    leagueID  int         not null,
    name      varchar(45) not null,
    constraint managerToLeague
        foreign key (leagueID) references league (leagueID)
            on update cascade on delete cascade,
    constraint managerToTeam
        foreign key (teamID) references team (teamID)
            on update cascade on delete cascade
);

create index managerToLeague_idx
    on manager (leagueID);

create index managerToTeam_idx
    on manager (teamID);

create table player
(
    playerID  int auto_increment
        primary key,
    teamID    int         not null,
    name      varchar(45) not null,
    position  varchar(45) not null,
    age       int         not null,
    skating   int         not null,
    shooting  int         not null,
    checking  int         not null,
    saving    int         not null,
    captain   tinyint     not null,
    strength  float       not null,
    isInjured tinyint(1)  not null,
    constraint playerToTeam
        foreign key (teamID) references team (teamID)
            on update cascade on delete cascade
);

create index playerToTeam_idx
    on player (teamID);

create index teamToDivision_idx
    on team (divisionID);

create procedure checkIfLeagueNameExists(IN leagueName varchar(200))
BEGIN
    select *
    from league
    where name = leagueName;
END;

create procedure freeToTeamTrade(IN idFree int, IN idTeam int, IN isCaptain tinyint(1), OUT status varchar(100),
                                 OUT lastID int)
BEGIN
    set IDFree = idFree;
    set IDTeam = idTeam;
    set Iscaptain = isCaptain;

    start transaction;

    insert into player (teamID, name, position, age, skating, shooting, checking, saving, captain, strength, isInjured)
    select IDTeam,
           name,
           position,
           age,
           skating,
           shooting,
           checking,
           saving,
           Iscaptain,
           strength,
           isInjured
    from freeagent
    where freeAgentID = IDFree;

    set lastID = last_insert_id();

    set SQL_SAFE_UPDATES = 0;
    delete from freeagent where freeAgentID = IDFree;
    SET SQL_SAFE_UPDATES = 1;
    commit;
    set status = "freeAgent transfered to Player list successfully";

    select status;
    select lastID;

END;

create procedure insertIntoCoach(IN teamID int, IN leagueID int, IN coachName varchar(45), IN skating decimal,
                                 IN shooting decimal, IN checking decimal, IN saving decimal, OUT coachID int)
BEGIN
    INSERT INTO coach VALUES (null, teamID, leagueID, coachName, skating, shooting, checking, saving);

    set coachID = last_insert_id();
END;

create procedure insertIntoConference(IN leagueID int, IN conferenceName varchar(45), OUT conferenceID int)
BEGIN
    INSERT INTO conference VALUES (NULL, leagueID, conferenceName);

    set conferenceID = last_insert_id();
END;

create procedure insertIntoDivision(IN conferenceID int, IN divisionName varchar(45), OUT divisionID int)
BEGIN
    INSERT INTO division VALUES (NULL, conferenceID, divisionName);

    set divisionID = last_insert_id();
END;

create procedure insertIntoFreeAgent(IN leagueID int, IN playerName varchar(45), IN pos varchar(45), IN age int,
                                     IN skating int, IN shooting int, IN checking int, IN saving int,
                                     IN strength decimal(3, 1), IN isInjured tinyint(1), OUT freeAgentID int)
BEGIN
    INSERT INTO freeagent
    VALUES (NULL, leagueID, playerName, pos, age, skating, shooting, checking, saving, strength, isInjured);

    set freeAgentID = last_insert_id();
END;

create procedure insertIntoLeague(IN leagueName varchar(45), OUT leagueID int)
BEGIN
    INSERT INTO league values (null, leagueName);

    set leagueID = last_insert_id();
END;

create procedure insertIntoManager(IN teamID int, IN leagueID int, IN managerName varchar(45), OUT managerID int)
BEGIN
    INSERT INTO manager VALUES (NULL, teamID, leagueID, managerName);

    set managerID = last_insert_id();
END;

create procedure insertIntoPlayer(IN teamID int, IN playerName varchar(45), IN pos varchar(45), IN age int,
                                  IN skating int, IN shooting int, IN checking int, IN saving int,
                                  IN captain tinyint(1), IN strength int, IN isInjured tinyint(1), OUT playerID int)
BEGIN
    INSERT INTO player
    VALUES (NULL, teamID, playerName, pos, age, skating, shooting, checking, saving, captain, strength, isInjured);

    set playerID = last_insert_id();
END;

create procedure insertIntoTeam(IN divisionID int, IN teamName varchar(45), IN strength int, OUT teamID int)
BEGIN
    INSERT INTO team VALUES (NULL, divisionID, teamName, strength);

    set teamID = last_insert_id();
END;

create procedure interTeamTrade(IN toTeamID int, IN IdPlayer int, OUT status varchar(45))
BEGIN
    set IDplayer = IdPlayer;
    start transaction;
    set SQL_SAFE_UPDATES = 0;
    update player set teamID=toTeamID where playerID = IDplayer;
    SET SQL_SAFE_UPDATES = 1;
    commit;
    set status = "Player transfered successfully";
    select status;
END;

create procedure loadConferences(IN ID int)
BEGIN
    select *
    from conference
    where leagueID = ID;
END;

create procedure loadDivisions(IN ID int)
BEGIN
    select *
    from division
    where conferenceID = ID;
END;

create procedure loadFreeAgents(IN ID int)
BEGIN
    select *
    from freeagent
    where leagueID = ID;
END;

create procedure loadLeague(IN ID int)
BEGIN
    select *
    from league
    where leagueID = ID;
END;

create procedure loadLeagueCoaches(IN ID int)
BEGIN
    select *
    from coach
    where leagueID = ID
      and teamID = null;
END;

create procedure loadLeagueManagers(IN ID int)
BEGIN
    select *
    from manager
    where leagueID = ID
      and teamID = null;
END;

create procedure loadTeamCoach(IN ID int)
BEGIN
    select *
    from coach
    where teamID = ID;
END;

create procedure loadTeamManager(IN ID int)
BEGIN
    select *
    from manager
    where teamID = ID;
END;

create procedure loadTeamPlayers(IN ID int)
BEGIN
    select *
    from player
    where teamID = ID;
END;

create procedure loadTeams(IN ID int)
BEGIN
    select *
    from team
    where divisionID = ID;
END;

create procedure teamToFreeTrade(IN idPlayer int, IN idLeague int, OUT status varchar(100), OUT lastID int)
BEGIN
    set IdPlayer = idPlayer;
    set IdLeague = idLeague;

    start transaction;
    set SQL_SAFE_UPDATES = 0;

    insert into freeagent (name, position, age, skating, shooting, checking, saving, strength, isInjured, leagueID)
    select name,
           position,
           age,
           skating,
           shooting,
           checking,
           saving,
           strength,
           isInjured,
           IdLeague
    from player
    where playerID = IdPlayer;

    set lastID = last_insert_id();

    delete from player where playerID = IdPlayer;

    SET SQL_SAFE_UPDATES = 1;
    commit;
    set status = "Player transfered to freeAgent list successfully";

    select status;
    select lastID;
END;

