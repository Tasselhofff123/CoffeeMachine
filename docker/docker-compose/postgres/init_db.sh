#!/bin/bash

if [ $( psql -U postgres -lqt | cut -d \| -f 1 | grep -w coffee_machine | wc -l ) = '0' ]; then
  createdb coffee_machine && pg_restore -d coffee_machine /coffee_DB.backup && echo "Database created"
else
  echo "Database exists"
fi