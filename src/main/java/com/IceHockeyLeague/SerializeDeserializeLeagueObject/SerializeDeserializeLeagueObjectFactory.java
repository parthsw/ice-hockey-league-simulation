package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

public class SerializeDeserializeLeagueObjectFactory implements ISerializeDeserializeLeagueObjectFactory {

    @Override
    public ISerialize createSerialize() {
        return new Serialize();
    }

    @Override
    public IDeserialize createDeserialize() {
        return new Deserialize();
    }
}
