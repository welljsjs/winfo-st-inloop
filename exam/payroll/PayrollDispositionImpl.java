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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PayrollDispositionImpl implements PayrollDisposition {
  private Map<Employee, Double> payments = new HashMap<Employee, Double>();

  public PayrollDispositionImpl() {

  }

  public double getTotal() {
    return payments.values().stream().reduce(0.0, Double::sum);
  }

  public double getAverage() {
    return payments.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
  }

  public Map<Employee, Double> getPayments() {
    return payments;
  }

  @Override
  public void sendPayment(Employee empl, double payment) {
    Objects.requireNonNull(empl, "empl must be non-null");
    if (payment <= 0)
      throw new IllegalArgumentException("Expected payment to be positive");
    Double currentPayment = payments.get(empl);
    System.err.println("Adding " + payment + " to payments...");
    payments.put(empl, currentPayment == null ? Double.valueOf(payment) : currentPayment + payment);
    System.err.println("After adding: size of payments: " + payments.size());
  }

}
