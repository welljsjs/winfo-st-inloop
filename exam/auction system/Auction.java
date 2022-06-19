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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Auction {
  private boolean closed;
  private List<Item> allItems = new ArrayList<Item>();
  private Set<Person> bidders = new HashSet<Person>();

  public void addBid(Item bidItem, String nameOfBidder, long price) {
    Objects.requireNonNull(bidItem, "bidItem must be non-null");
    Objects.requireNonNull(nameOfBidder, "nameOfBidder must be non-null");
    if (nameOfBidder.isEmpty())
      throw new IllegalArgumentException("nameOfBidder must be non-empty");
    if (price < 0)
      throw new IllegalArgumentException("price must be non-negative");
    if (closed)
      throw new IllegalStateException("Auction is closed.");
    if (!allItems.contains(bidItem))
      throw new NoSuchElementException("Auction does not contain specified bidItem");

    Person bidder = new Person(nameOfBidder);
    bidItem.addBid(bidder, price);
    bidders.add(bidder);
  }

  public String closeAuction() {
    if (closed)
      throw new IllegalStateException("Auction is already closed");
    closed = true;
    return generateItemListString();
  }

  public String generateAllBidsString(Item item) {
    Objects.requireNonNull(item, "item must be non-null");
    List<Bid> allBids = item.getAllBids();
    return allBids.isEmpty() ? "All bids:"
        : "All bids:\n" + allBids.stream().map(Bid::toString).collect(Collectors.joining("\n"));
  }

  public String generateItemListString() {
    String itemListString = "";
    for (Item item : allItems)
      itemListString += generateItemString(item) + "\n";
    return itemListString;
  }

  public void registerItem(Item item) {
    Objects.requireNonNull(item, "item must be non-null");
    if (closed)
      throw new IllegalStateException("Auction is closed.");
    if (allItems.contains(item))
      throw new IllegalArgumentException("Item with same name does already exist in auction!");
    allItems.add(item);

  }

  public abstract String generateItemString(Item item);

  public List<Item> getAllItems() {
    return allItems;
  }

}