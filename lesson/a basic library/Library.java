import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Copyright 2022 Julius Schmidt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class Library {
  private List<Book> books = new ArrayList<Book>();

  public Library() {
    System.out.println("Hello, I am a library, which can store up to 10 books!");
  }

  public void add(Book book) {
    if (book == null)
      throw new IllegalArgumentException("book must be non-null");

    if (books.size() == 10) {
      System.out.println("The library is full!");
      return;
    }

    books.add(book);

    System.out.println("I added the book " + book.getTitle() + "!");
  }

  public Book search(String title) {
    if (title == null || title.isEmpty())
      throw new IllegalArgumentException("title must be non-null and non-empty");

    Book foundBook = null;
    for (Book book : books)
      if (book.getTitle().equals(title)) {
        foundBook = book;
        break;
      }

    System.out.println(
        "The book with the title " + title + (foundBook == null ? " does not exist " : " exists ") + "in the library!");

    return foundBook;
  }
}