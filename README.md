Консольная утилита для вывода характеристик геометрической фигуры.  

Принимаемые параметры:
### `*` `-` Не обязательный параметр
 - inputFilePath - Путь до файла со входными характеристиками
 - *printMethod - Куда будет произведен вывод. Может принимать одно из значений CONSOLE - Вывод в консоль, FILE - вывод в файл (Если параметр не передан, по дефолту выводим в консоль)
 - *saveFilePath - Путь до файла для сохранения результата (Передается только в том случае, если printMethod передан = FILE)

Пример передачи параметров

```-DprintMethod=FILE,-DinputFilePath=src/test/resources/fixture/input.txt,-DsaveFilePath=src/test/resources/fixture/output.txt```