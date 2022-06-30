package collections2;
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

public class Book implements Comparable<Book> {
  private String isbn;
  private String author = "";
  private String title = "";

  public Book(String isbn) {
    if (isbn == null || isbn.isEmpty())
      throw new IllegalArgumentException("isbn must be non-null and non-empty");

    this.isbn = isbn;
  }

  public Book(String isbn, String author, String title) {
    if (isbn == null || isbn.isEmpty())
      throw new IllegalArgumentException("isbn must be non-null and non-empty");
    if (author == null || author.isEmpty())
      throw new IllegalArgumentException("author must be non-null and non-empty");
    if (title == null || title.isEmpty())
      throw new IllegalArgumentException("title must be non-null and non-empty");

    this.isbn = isbn;
    this.author = author;
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return title + " by " + author + " (ISBN " + isbn + ")";
  }

  @Override
  public int hashCode() {
    return isbn.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Book))
      return false;
    return isbn.equals(((Book) obj).isbn);
  }

  @Override
  public int compareTo(Book o) {
    if (o == null)
      throw new IllegalArgumentException("o must be non-null to compare");
    return isbn.compareTo(o.isbn);
  }
}