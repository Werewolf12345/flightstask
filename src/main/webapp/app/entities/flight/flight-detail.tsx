import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './flight.reducer';
import { IFlight } from 'app/shared/model/flight.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFlightDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FlightDetail extends React.Component<IFlightDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { flightEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Flight [<b>{flightEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="flight">Flight</span>
            </dt>
            <dd>{flightEntity.flight}</dd>
            <dt>
              <span id="departure">Departure</span>
            </dt>
            <dd>{flightEntity.departure}</dd>
          </dl>
          <Button tag={Link} to="/entity/flight" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/flight/${flightEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ flight }: IRootState) => ({
  flightEntity: flight.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FlightDetail);
