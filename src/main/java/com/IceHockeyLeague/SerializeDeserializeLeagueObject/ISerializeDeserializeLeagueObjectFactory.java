package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

public interface ISerializeDeserializeLeagueObjectFactory {
    ISerialize createSerialize();
    IDeserialize createDeserialize();
}
