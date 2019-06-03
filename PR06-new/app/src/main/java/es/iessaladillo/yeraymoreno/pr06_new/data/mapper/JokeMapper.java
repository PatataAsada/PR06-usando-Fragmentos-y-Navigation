package es.iessaladillo.yeraymoreno.pr06_new.data;

import es.iessaladillo.yeraymoreno.pr06_new.data.model.Joke;
import es.iessaladillo.yeraymoreno.pr06_new.data.remote.dto.JokeDto;

class JokeMapper {
    public static Joke map(Joke data) {

        
    }

    public Joke map(JokeDto jokeDto){
        return new Joke(jokeDto.getIcon_url(),jokeDto.getId(),jokeDto.getUrl(),jokeDto.getValue());
    }
}
