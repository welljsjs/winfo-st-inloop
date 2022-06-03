interface ISalePricing {
  /**
   * 
   * @param sale
   * @return Return total in cents.
   */
  public long getTotal(Sale sale);
}