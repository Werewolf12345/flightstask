import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Flight from './flight';
import FlightDetail from './flight-detail';
import FlightUpdate from './flight-update';
import FlightDeleteDialog from './flight-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FlightUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FlightUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FlightDetail} />
      <ErrorBoundaryRoute path={match.url} component={Flight} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FlightDeleteDialog} />
  </>
);

export default Routes;
