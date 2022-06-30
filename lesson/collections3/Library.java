// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package collections3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Library {
  private Map<String, Set<Book>> stock = new TreeMap<String, Set<Book>>();

  public boolean insertBook(Book newBook) {
    if (newBook == null)
      return false;

    Set<Book> oldSet;
    if ((oldSet = stock.putIfAbsent(newBook.getAuthor(), new HashSet<Book>(Arrays.asList(newBook)))) != null)
      return oldSet.add(newBook);
    return true;
  }

  public Book searchForIsbn(String isbn) {
    if (isbn == null || isbn.isEmpty())
      throw new IllegalArgumentException("isbn must be non-null and non-empty");

    return stock.values().stream().flatMap(Collection::stream).filter((new Book(isbn))::equals).findAny().orElse(null);
  }

  public Collection<Book> searchForAuthor(String author) {
    if (author == null || author.isEmpty())
      throw new IllegalArgumentException("author must be non-null and non-empty");
    Collection<Book> foundBooks = stock.get(author);
    return foundBooks == null ? Collections.emptyList() : foundBooks;
  }

  public Map<String, Set<Book>> listStockByAuthor() {
    return stock;
  }

  public Collection<Book> getStock() {
    return stock.values().stream().flatMap(Collection::stream).distinct().sorted().toList();
  }
}
