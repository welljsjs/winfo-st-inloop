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

public class Appointee extends Employee {
  private int payday;
  private int hoursPerMonth;
  private double payPerHour;

  public Appointee(String id, int payday, int hoursPerMonth, double payPerHour) {
    super(id);
    if (payday < 1 || payday > 30 || hoursPerMonth <= 0 || payPerHour <= 0)
      throw new IllegalArgumentException(
          "Expected payday to be between 1 and 30 and hoursPerMonth and payPerHour to be non-negative");

    this.payday = payday;
    this.hoursPerMonth = hoursPerMonth;
    this.payPerHour = payPerHour;
  }

  @Override
  public boolean isPayday(int dayOfMonth) {
    if (dayOfMonth < 1 || dayOfMonth > 30)
      throw new IllegalArgumentException("dayOfMonth must be between 1 and 30 (inclusive)");
    return payday == dayOfMonth;
  }

  @Override
  public double calculatePay() {
    return hoursPerMonth * payPerHour;
  }

  @Override
  public double calculateDeductions() {
    return 0.4 * calculatePay();
  }
}
