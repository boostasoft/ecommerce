import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './customer-details.reducer';
import { ICustomerDetails } from 'app/shared/model/customer-details.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICustomerDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomerDetailsDetail = (props: ICustomerDetailsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { customerDetailsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          CustomerDetails [<b>{customerDetailsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="gender">Gender</span>
          </dt>
          <dd>{customerDetailsEntity.gender}</dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{customerDetailsEntity.phone}</dd>
          <dt>
            <span id="addressLine1">Address Line 1</span>
          </dt>
          <dd>{customerDetailsEntity.addressLine1}</dd>
          <dt>
            <span id="addressLine2">Address Line 2</span>
          </dt>
          <dd>{customerDetailsEntity.addressLine2}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{customerDetailsEntity.city}</dd>
          <dt>
            <span id="country">Country</span>
          </dt>
          <dd>{customerDetailsEntity.country}</dd>
          <dt>User</dt>
          <dd>{customerDetailsEntity.user ? customerDetailsEntity.user.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/customer-details" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer-details/${customerDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ customerDetails }: IRootState) => ({
  customerDetailsEntity: customerDetails.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomerDetailsDetail);
