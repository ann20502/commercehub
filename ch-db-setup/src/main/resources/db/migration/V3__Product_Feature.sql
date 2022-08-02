CREATE TABLE item
(
    item_id INT NOT NULL,
    category_id INT NOT NULL,
    name STRING NOT NULL,
    description STRING NOT NULL,
    sku STRING,
    create_time TIMESTAMP NOT NULL,
    update_time TIMESTAMP,
    extract_time TIMESTAMP,
    attributes ARRAY<STRUCT<
        attribute_id INT,
        original_attribute_name STRING,
        is_mandatory BOOL,
        attribute_values ARRAY<STRUCT<
            attribute_value_id INT,
            original_value_name STRING,
            value_unit STRING
        >>
    >>,
    prices ARRAY<STRUCT<
        currency STRING,
        original_price NUMERIC,
        current_price NUMERIC,
        inflated_price_of_current_price NUMERIC,
        sip_item_price NUMERIC,
        sip_item_price_source NUMERIC
    >>,
    image STRUCT<
        image_urls ARRAY<STRING>,
        image_ids ARRAY<STRING>
    >,
    weight STRING,
    dimension STRUCT<
        length INT,
        width INT,
        height INT
    >,
    pre_order STRUCT<
        is_pre_order BOOL,
        days_to_ship INT
    >,
    wholesales ARRAY<STRUCT<
        min_count INT,
        max_count INT,
        unit_price NUMERIC,
        inflated_price_of_unit_price NUMERIC
    >>,
    condition STRING,
    size_chart STRING,
    item_status STRING,
    has_model BOOL,
    promotion_id INT,
    videos ARRAY<STRUCT<
        video_url STRING,
        thumbnail_url STRING,
        duration INT
    >>,
    brand STRUCT<
        brand_id INT,
        original_brand_name STRING
    >,
    item_dangerous INT,
    stock_summary STRUCT<
        total_reserved_stock INT,
        total_available_stock INT
    >,
    stock_seller STRUCT<
        location_id STRING,
        stock INT
    >,
    stock_shopee STRUCT<
        location_id STRING,
        stock INT
    >
)