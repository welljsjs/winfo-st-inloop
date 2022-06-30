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

package collections1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Library {
  private List<Book> stock = new ArrayList<Book>();

  public boolean insertBook(Book newBook) {
    if (newBook == null)
      return false;
    stock.add(newBook);
    Collections.sort(stock);
    return true;
  }

  public Book searchForIsbn(String isbn) {
    if (isbn == null || isbn.isEmpty())
      throw new IllegalArgumentException("isbn must be non-null and non-empty");

    int index = Collections.binarySearch(stock, new Book(isbn));
    return index < 0 ? null : stock.get(index);
  }

  public Collection<Book> searchForAuthor(String author) {
    if (author == null || author.isEmpty())
      throw new IllegalArgumentException("author must be non-null and non-empty");

    return stock.stream().filter(b -> b.getAuthor().equals(author)).toList();
  }

}
