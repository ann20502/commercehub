CREATE TABLE shop_performance
(
	extract_time TIMESTAMP NOT NULL,
	overall_performance INT NOT NULL,
    listing_overall STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
    >,
	listing_spam STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	listing_counterfeit STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	listing_prohibited STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	listing_pre_order_percent STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	listing_pre_order_exceed_target STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	listing_other STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	non_fulfillment_overall STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	non_fulfillment_cancellation STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	non_fulfillment_return_refund STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	fulfillment_preparation_time STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	fulfillment_late_shipment STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	cservice_response_overall STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	cservice_response_time STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>,
	csatisfaction_rating_overall STRUCT<
		target STRING,
		performance STRING,
		penalty_point STRING
	>
)