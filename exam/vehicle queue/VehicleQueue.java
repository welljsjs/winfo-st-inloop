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

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class VehicleQueue implements ClockObserver {
  private double entryDelay;
  private double exitDelay;
  private int trafficLightRate;
  private boolean greenLight;
  private VehicleGenerator generator;
  private Queue<Vehicle> queue = new LinkedList<Vehicle>();

  public VehicleQueue(double entryDelay, double exitDelay, int trafficLightRate, VehicleGenerator generator) {
    if (entryDelay <= 0 || exitDelay <= 0 || trafficLightRate <= 0)
      throw new IllegalArgumentException("entryDelay, exitDelay and trafficLightRate must be positive");
    this.generator = Objects.requireNonNull(generator, "generator must be non-null");
    this.entryDelay = entryDelay;
    this.exitDelay = exitDelay;
    this.trafficLightRate = trafficLightRate;
  }

  public void enter() {
    queue.add(generator.createVehicle());
  }

  public void leave() {
    queue.poll();
  }

  public double getLength() {
    return queue.stream().mapToDouble(Vehicle::getLength).sum();
  }

  public int getSize() {
    return queue.size();
  }

  @Override
  public void tick(int currentTime) {
    if (currentTime < 0)
      throw new IllegalArgumentException("currentTime must be non-negative");
    final int lastTickTime = Math.max(0, currentTime - 1); // this is unnecessary since currentTime should always be
                                                           // at least one.

    // 1. vehicles can always enter.
    DoubleStream.iterate(0d, d -> d <= currentTime, d -> Math.round(100 * (d + entryDelay)) / 100d)
        .filter(d -> d > lastTickTime)
        .forEach(d -> {
          System.err.println("vehicle entering at: " + d);
          enter();
        }); // vehicles can always enter every 'entryDelay' seconds.

    // 2. let vehicles leave if there is (was) a green light only.
    if (greenLight) {
      final int lastLightChangeTime = IntStream.rangeClosed(0, lastTickTime)
          .filter(tick -> tick % trafficLightRate == 0)
          .max().orElse(0); // closed light change to lastTickTime to green light.
      System.err.println("last light change time: " + lastLightChangeTime);

      DoubleStream
          .iterate(lastLightChangeTime, // starting from last light change to green.
              d -> d <= currentTime, d -> Math.round(100 * (d + exitDelay)) / 100d) // NOTE rounding is essential
                                                                                    // (running into floating point
                                                                                    // precision problem)
          .filter(d -> d > lastTickTime)
          .forEach(d -> {
            System.err.println("vehicle leaving at: " + d);
            leave();
          }); // vehicles can always enter every 'entryDelay' seconds, starting at
              // 'lastTickTime'!
    }

    // 3. change light __after__ letting vehicles leave (possibly).
    if (currentTime % trafficLightRate == 0)
      greenLight = !greenLight; // traffic light changes immediately every 'trafficLightRate' seconds (only even
                                // seconds, that is integer values).

  }

}