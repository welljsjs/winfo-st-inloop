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

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Clock {
  private int currentTime;
  private int endOfTime;
  private Set<ClockObserver> observers = new HashSet<ClockObserver>();

  public Clock(int endOfTime) {
    if (endOfTime <= 0)
      throw new IllegalArgumentException("endOfTime must be positive");
    this.endOfTime = endOfTime;
  }

  public void addObserver(ClockObserver observer) {
    Objects.requireNonNull(observer, "observer must be non-null");
    observers.add(observer);
  }

  public void removeObserver(ClockObserver observer) {
    Objects.requireNonNull(observer, "observer must be non-null");
    observers.remove(observer);
  }

  public int getCurrentTime() {
    return currentTime;
  }

  public void run() {
    currentTime = 0; // reset clock at the start of each run.
    while (currentTime < endOfTime)
      tick(++currentTime); // prefix increment operator necessary
  }

  private void tick(int currentTime) {
    observers.forEach(observer -> observer.tick(currentTime)); // can't use method references with parameters, have to
                                                               // resort to lambda expressions.
  }

}
