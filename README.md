# SpringMusicStreamingApplication
Realization of music streaming app with Java Spring

## Теория

Классы в папке entity: Каждый объект класса представляет собой одну запись (строку) в таблице

Классы в папке service: Содержат логику приложения. То что можно делать с соответствующими им классами из entity.

Классы в папке controller: реагируют на запросы к ссылкам (например localhost:7979/albums, localhost:7979/songs),
вызывают классы из service для обработки и возвращают json ответ.

Классы в папке repository: прослойки для Hibernate, которые сами генерируют SQL код для наших методов.

## TODO:

resolve infinity recursions in entities with annotation @JsonBackReference and @JsonManagedReference


## Entities
songs, albums, artists, playlists, user