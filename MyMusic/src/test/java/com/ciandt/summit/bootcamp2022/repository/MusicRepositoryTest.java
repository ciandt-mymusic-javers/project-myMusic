package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MusicRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    MusicRepository musicRepository;

    @Test
    public void findMusicsByNameOrArtistsNoCaseSensitiveTest(){

        Artist artist = new Artist("30ab1678-c616-4314-adcc-918aff5a7a13", "Nickelback");
        entityManager.persist(artist);
        Music music = new Music("4ffb5d4f-8b7f-4996-b84b-ecf751f52eea", "Photograph", artist);
        entityManager.persist(music);

        Page<Music> musicLowerCase = musicRepository.findMusicsByNameOrArtists("Nickelback", PageRequest.of(0, 10));
        Page<Music> musicUpperCase = musicRepository.findMusicsByNameOrArtists("NICKELBACK", PageRequest.of(0, 10));

        Assert.assertEquals(musicLowerCase, musicUpperCase);
    }
}
