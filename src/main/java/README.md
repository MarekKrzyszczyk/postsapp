Aplikację można uruchomić lokalnie. 
Baza danych znajduję się na zewnętrznym serwerze, a dane potrzebne do połączenia są w pliku: application.properties.

Spis funkcjonalności:
- pobranie listy postów: GET(http://localhost:8080/api/posts)
- pobranie listy postów z sortowaniem po tytule rosnąco: GET(http://localhost:8080/api/posts?sortByTitle=asc)
- pobranie listy postów z sortowaniem po tytule malejąco: GET(http://localhost:8080/api/posts?sortByTitle=desc)
- usuwanie postu po id: DELETE(http://localhost:8080/api/posts/{id})
- edytowanie tytułu oraz tekstu postu: PUT(http://localhost:8080/api/posts/{id}?newTitle=value1&newBody=value2), parametry są opcjonalne
- aktualizacja i dodawanie postów: POST(http://localhost:8080/api/posts), dodatkowo codziennie, o godzinie 23:45:00 aktualizacja odbywa się automatycznie

Uwagi:
- do encji POST dodałem pola edited i deleted, aby było wiadomo, które posty zostały usunięte lub zaktualizowane
- przy pobieraniu listy postów, zwracany jest obiekt PostDto, tak aby nie były widoczne pola: userId, edited, deleted (wykorzystanie mapstruct)
- w aplikacji próbowałem umieścić testy jednostkowe, jednak nie ma ich dużo. Wynika to nie z faktu braku czasu, ale zbyt małej mojej wiedzy na ten temat. 
Jeśli jest taka możliwość to będę bardzo wdzięczny za podrzucenie materiałów, gdzie będe mógł poszerzyć swoją wiedzę w tym obszarze, a najlepiej byłoby zobaczyć 1 przykład jak powinno to być zrobione w prawidłowy sposób.

