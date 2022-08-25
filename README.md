<h1 align="center"><a href="#">🔗 MyMusic</a></h1>

<p align="center">🚀 A new Spotify-like API that allows you to search for musics and artists as well as create new playlists</p>

------



<h4 align="center"> 
	🚧  MyMusic 🚀 Under construction...  🚧
</h4>
<div align="center"><div><a href="https://circleci.com/gh/circleci/circleci-docs"><img src="https://circleci.com/gh/circleci/circleci-docs.svg?style=shield"></a>&nbsp;<img alt="GitHub last commit (branch)" src="https://img.shields.io/github/last-commit/ciandt-mymusic-javers/project-myMusic/develop">&nbsp;<img alt="GitHub top language" src="https://img.shields.io/github/languages/top/ciandt-mymusic-javers/project-myMusic"><a href="https://www.repostatus.org/#wip">&nbsp;<img src="https://www.repostatus.org/badges/latest/wip.svg" alt="Project Status: WIP – Initial development is in progress, but there has not yet been a stable, usable release suitable for the public." /></a></div></div>

## Features

- [x] Allow users to fetch new songs from database
- [ ] Allow the users to choose the songs and add to their playlist
- [ ] Allow users to remove songs from their playlist
- [ ] Create premium and common user profile

## Business Rules

- Allow users to fetch new songs from database
  - The service must validate that the user has entered at least 3 characters, returning an HTTP 400 if the query has less than 2 characters
  - The search must be performed in both the artist name and song name columns
  - Music search should not be case sensitive
  - The search must return values containing the filter, not needing to inform the full name of the song or artist
  - The return must be sorted by artist name and then by song name
- Allow the users to choose the songs and add to their playlist
  - ...

## How to Install

- Intall [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)

* To download the project follow the instructions bellow:

```
1. git clone https://github.com/ciandt-mymusic-javers/project-myMusic.git
2. cd project-myMusic
```



## Contributors
<div>
<table>
    <tr>
        <td><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/108934041?v=4" width="100px;" alt=""/> </td>
	<td><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/109691986?v=4" width="100px;" alt=""/>  </td>
	<td><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/108896473?v=4" width="100px;" alt=""/></td>
	<td><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/109041193?v=4" width="100px;" alt=""/></td>
    </tr>
    <tr>
    <td>
     <div align="center">
            <sub><b>José Raimundo</b></sub>
        </div>
    </td>
   <td>
     <div align="center">
            <sub><b>Bruna Araújo</b></sub>
        </div>
    </td>
    <td>
     <div align="center">
            <sub><b>Sarah Ferreira</b></sub>
        </div>
    </td>
    <td>
     <div align="center">
            <sub><b>Vitor Lima</b></sub>
        </div>
    </td>
    </tr>
</table>
</div>
