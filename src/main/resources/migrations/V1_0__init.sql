create table if not exists users
(
    user_id    uuid primary key,
    name       varchar not null
);
comment on table users is 'Пользователи';
comment on column users.user_id is 'Идентификатор пользователя';
comment on column users.name is 'Полное имя пользователя';

create table if not exists subscriptions
(
    subscription_id uuid primary key,
    user_id              uuid not null references users,
    name                 varchar not null,
    constraint subscriptions_user_id_name_uq unique(user_id, name)
);
comment on table subscriptions is 'Подписки';
comment on column subscriptions.subscription_id is 'Идентификатор подписки';
comment on column subscriptions.user_id is 'Внешний ключ на пользователя';
comment on column subscriptions.name is 'Название подписки';

create index subscriptions_user_id_idx on subscriptions (user_id);
