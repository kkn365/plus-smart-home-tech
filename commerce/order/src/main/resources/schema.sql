CREATE USER order_app WITH PASSWORD 'Pa$$word';
CREATE DATABASE order_db;
GRANT ALL PRIVILEGES ON DATABASE order_db TO order_app;

CREATE TABLE IF NOT EXISTS orders (
    order_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    shopping_cart_id UUID,
    payment_id UUID,
    delivery_id UUID,
    state VARCHAR(15),
    username VARCHAR(100),
    delivery_weight DOUBLE PRECISION,
    delivery_volume DOUBLE PRECISION,
    fragile BOOLEAN,
    total_price NUMERIC,
    delivery_price NUMERIC,
    product_price NUMERIC
);

CREATE TABLE IF NOT EXISTS order_products (
    order_id UUID REFERENCES orders(order_id),
    product_id UUID,
    quantity BIGINT
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO order_app;